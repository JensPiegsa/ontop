package it.unibz.inf.ontop.spec.mapping;

import it.unibz.inf.ontop.dbschema.DBMetadata;
import it.unibz.inf.ontop.exception.InvalidMappingSourceQueriesException;
import it.unibz.inf.ontop.iq.tools.ExecutorRegistry;
import it.unibz.inf.ontop.spec.mapping.pp.PreProcessedMapping;
import it.unibz.inf.ontop.spec.mapping.pp.PreProcessedTriplesMap;

/**
 * TODO: explain
 */
public interface PPMappingConverter<T extends PreProcessedTriplesMap, M extends PreProcessedMapping<T>> {

    MappingWithProvenance convert(M ppMapping, DBMetadata dbMetadata, ExecutorRegistry executorRegistry) throws InvalidMappingSourceQueriesException;

}
