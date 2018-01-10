databaseChangeLog = {

    changeSet(author: "Daniel (generated)", id: "1515515560825-1") {
        createSequence(sequenceName: "hibernate_sequence")
    }

    changeSet(author: "Daniel (generated)", id: "1515515560825-2") {
        createTable(tableName: "message") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "messagePK")
            }

            column(name: "message", type: "VARCHAR(255)")

            column(name: "date_created", type: "timestamp")

            column(name: "last_updated", type: "timestamp")
        }
    }
}