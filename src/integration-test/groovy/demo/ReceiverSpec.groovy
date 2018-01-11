package demo

import grails.plugins.rest.client.RestBuilder
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import grails.util.Holders
import spock.lang.Specification

/**
 * Created by Daniel on 10/01/2018.
 */
@Integration
@Rollback
class ReceiverSpec extends Specification {
    String baseUrl
    RestBuilder rest
    String accessToken
    String jsonStringFromFile

    def setup() {

       // String baseUrl1 = Holders.grailsApplication.config.grails.serverURL
        baseUrl = 'http://localhost:9090'
        jsonStringFromFile =  new File('./json/sample_json1.json').text

        rest = new RestBuilder()
        def response = rest.post("${baseUrl}/oauth/token?grant_type=password&password=xyz&username=bobbywarner&scope=read write") {
            auth('demo-client', '123456')
            accept("application/json")
        }

        response.status == 200
        assert 'read write' == response.json.scope
        assert 'bearer' == response.json.token_type
        accessToken = "Bearer $response.json.access_token"
    }

    void "get profile to prove access token is OK"() {
        when:
        def response = rest.get("${baseUrl}/profile") {
            header("Authorization", accessToken)
        }

        then:
        response.status == 200
    }


    void "send JSON from file to receiver"() {
        when:
        def response = rest.post("${baseUrl}/receiver") {
            header("Authorization", accessToken)
            contentType('application/json')
            body(jsonStringFromFile)
        }

        then:
        response.status == 200
        response.text == 'message received'
    }



    void "unauthorized post"() {
        when:
        def response = rest.post("${baseUrl}/receiver") {
            //header("Authorization", accessToken)
        }

        then:
        response.status == 401
    }
}
