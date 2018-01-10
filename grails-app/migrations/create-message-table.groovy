databaseChangeLog = {

    changeSet(author: "Daniel (generated)", id: "1515581497870-1") {
        createSequence(sequenceName: "hibernate_sequence")
    }

    changeSet(author: "Daniel (generated)", id: "1515581497870-2") {
        createTable(tableName: "message") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "messagePK")
            }

            column(name: "message", type: "CLOB")

            column(name: "date_created", type: "timestamp")

            column(name: "last_updated", type: "timestamp")
        }
    }
}