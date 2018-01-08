package demo

class Message {

    String message

    Date dateCreated
    Date lastUpdated

    static constraints = {
        message nullable: true
        dateCreated nullable: true
        lastUpdated nullable: true
    }

    static mapping = {
        version false
    }
}
