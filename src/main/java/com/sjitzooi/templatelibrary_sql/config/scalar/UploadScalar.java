package com.sjitzooi.templatelibrary_sql.config.scalar;

import com.netflix.graphql.dgs.DgsScalar;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import org.springframework.web.multipart.MultipartFile;

@DgsScalar(name = "Upload")
public class UploadScalar implements Coercing<MultipartFile, Void> {
    @Override
    public Void serialize(Object dataFetcherResult) {
        throw new CoercingSerializeException("Upload is an input-only type and cannot be serialized");
    }

    @Override
    public MultipartFile parseValue(Object input) {
        if (input instanceof MultipartFile) {
            return (MultipartFile) input;
        }
        throw new CoercingParseValueException("Expected type MultipartFile but was " + input.getClass().getName());
    }

    @Override
    public MultipartFile parseLiteral(Object input) {
        throw new CoercingParseLiteralException("Upload is an input-only type and cannot be parsed from literals");
    }
}
