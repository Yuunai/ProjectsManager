package pl.poznan.put.student.spacjalive.erp.mongo.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.poznan.put.student.spacjalive.erp.mongo.entity.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class LogDAOImpl implements LogDAO {

    Logger logger = Logger.getLogger(LogDAOImpl.class.getSimpleName());

    @Autowired
    MongoDatabase mongoDatabase;

    @Override
    public List<Log> getLogs() {
        List<Log> logs = new ArrayList<>();

        MongoCollection<Document> collection = mongoDatabase.getCollection("logs");
        MongoCursor<Document> cursor = collection.find().iterator();

        Document tempDoc;

        try {
            while (cursor.hasNext()) {
                tempDoc = cursor.next();
                logs.add(new Log((String)tempDoc.get("message"), LocalDateTime.parse((String)tempDoc.get("date"), DateTimeFormatter.ISO_DATE_TIME)));
            }
        } finally {
            cursor.close();
        }

        return logs;
    }

    @Override
    public void saveLog(Log log) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("logs");
        Document document = new Document("date", log.getDateTime().toString()).append("message", log.getLog());

        collection.insertOne(document);
    }
}
