package ru.airlightvt.onlinerecognition.storage.service;

import com.google.common.collect.Lists;
import com.google.common.net.MediaType;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonObjectId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import ru.airlightvt.onlinerecognition.storage.data.StorageSchema;
import ru.airlightvt.onlinerecognition.storage.data.UploadFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Component
@Data
@Slf4j
@RequiredArgsConstructor
public class MongoGridFsService implements IStorageService {
    private final GridFsTemplate gridFsTemplate;

    private static Query getFilenameQuery(String name) {
        return Query.query(GridFsCriteria.whereFilename().is(name));
    }

    @Override
    public void store(byte[] data, String filename) {

    }

    public void createOrUpdate(byte[] data, String filename) {
        maybeLoadFile(filename)
                .ifPresent(p -> gridFsTemplate.delete(getFilenameQuery(filename)));
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        ObjectId objectId = gridFsTemplate.store(inputStream, filename, MediaType.ANY_TYPE);
    }

    public List<String> list() {
        MongoCursor<GridFSFile> iterator = getFiles().iterator();
        List<String> result = Lists.newArrayList();
        while(iterator.hasNext()) {
            GridFSFile gridFSFile = iterator.next();
            result.add(gridFSFile.getFilename());
        }
        return result;
    }

    @Override
    public UploadFile load(Long id) {
        return null;
    }

    @Override
    public List<UploadFile> loadAll() {
        MongoCursor<GridFSFile> iterator = getFiles().iterator();
        List<UploadFile> result = Lists.newArrayList();
        while(iterator.hasNext()) {
            GridFSFile gridFSFile = iterator.next();
            UploadFile uploadFile = new UploadFile();
            uploadFile.setSchema(StorageSchema.MONGO_GRID);
            uploadFile.setFileName(gridFSFile.getFilename());
            try(ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                InputStream inputStream = gridFsTemplate.getResource(gridFSFile).getInputStream();
                int nRead;
                byte[] data = new byte[1024];
                while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                    os.write(data, 0, nRead);
                }
                os.flush();
                uploadFile.setData(os.toByteArray());
            } catch (IOException e) {
                e.printStackTrace(); // todo change method interface
            }
            result.add(uploadFile);
        }
        return result;
    }

    @Override
    public void deleteAll() {
        gridFsTemplate.delete(null);
    }

    private GridFSFindIterable getFiles() {
        return gridFsTemplate.find(null);
    }

    private Optional<GridFSFile> maybeLoadFile(String name) {
        GridFSFile file = gridFsTemplate.findOne(getFilenameQuery(name));
        return Optional.ofNullable(file);
    }
}
