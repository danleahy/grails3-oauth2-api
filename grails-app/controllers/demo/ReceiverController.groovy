package demo

import grails.converters.JSON
import groovy.json.JsonSlurper

class ReceiverController {

    static allowedMethods = [save: "POST"]

    def save(){
        String newrequest = request.reader.text

        def object

        if(newrequest){
            def jsonSlurper = new JsonSlurper()
            object = jsonSlurper.parseText(newrequest)
        }

        Message message = new Message()
        message.message = newrequest

        message.save(flush:true)

        render(status: 200, text: "message received") as JSON
    }
}
