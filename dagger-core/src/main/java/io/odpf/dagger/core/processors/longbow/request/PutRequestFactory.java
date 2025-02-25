package io.odpf.dagger.core.processors.longbow.request;

import io.odpf.dagger.core.processors.longbow.LongbowSchema;
import io.odpf.dagger.core.processors.longbow.storage.PutRequest;
import io.odpf.dagger.core.sink.ProtoSerializer;

import org.apache.flink.types.Row;

import java.io.Serializable;

/**
 * The factory class for put request.
 */
public class PutRequestFactory implements Serializable {

    private LongbowSchema longbowSchema;
    private ProtoSerializer protoSerializer;
    private String tableId;

    /**
     * Instantiates a new Put request factory.
     *
     * @param longbowSchema   the longbow schema
     * @param protoSerializer the proto serializer
     * @param tableId         the table id
     */
    public PutRequestFactory(LongbowSchema longbowSchema, ProtoSerializer protoSerializer, String tableId) {
        this.longbowSchema = longbowSchema;
        this.protoSerializer = protoSerializer;
        this.tableId = tableId;
    }

    /**
     * Create put request.
     *
     * @param input the input
     * @return the put request
     */
    public PutRequest create(Row input) {
        if (!longbowSchema.isLongbowPlus()) {
            return new TablePutRequest(longbowSchema, input, tableId);
        } else {
            return new ProtoBytePutRequest(longbowSchema, input, protoSerializer, tableId);
        }
    }
}
