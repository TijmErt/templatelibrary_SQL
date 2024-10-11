package com.sjitzooi.templatelibrary_sql.config.scalar;

import com.netflix.graphql.dgs.DgsScalar;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@DgsScalar(name = "Date")
public class DateScalar implements Coercing<LocalDate, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public String serialize(Object dataFetcherResult) {
        if (dataFetcherResult instanceof LocalDate) {
            return FORMATTER.format((LocalDate) dataFetcherResult);
        }
        throw new CoercingSerializeException("Expected a LocalDate object.");
    }

    @Override
    public LocalDate parseValue(Object input) {
        try {
            return LocalDate.parse(input.toString(), FORMATTER);
        } catch (Exception e) {
            throw new CoercingParseValueException("Expected a valid ISO date.");
        }
    }

    @Override
    public LocalDate parseLiteral(Object input) {
        if (input instanceof StringValue) {
            try {
                return LocalDate.parse(((StringValue) input).getValue(), FORMATTER);
            } catch (Exception e) {
                throw new CoercingParseLiteralException("Expected a valid ISO date.");
            }
        }
        throw new CoercingParseLiteralException("Expected a StringValue.");
    }
}
