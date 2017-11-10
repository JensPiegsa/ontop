package it.unibz.inf.ontop.injection.impl;


import com.google.common.collect.ImmutableList;
import com.google.inject.Module;
import it.unibz.inf.ontop.injection.NativeQueryLanguageComponentFactory;
import it.unibz.inf.ontop.injection.SQLPPMappingFactory;
import it.unibz.inf.ontop.injection.OntopMappingSQLConfiguration;
import it.unibz.inf.ontop.injection.OntopMappingSQLSettings;
import it.unibz.inf.ontop.spec.mapping.pp.SQLPPMappingConverter;
import it.unibz.inf.ontop.spec.mapping.parser.SQLMappingParser;
import it.unibz.inf.ontop.spec.dbschema.RDBMetadataExtractor;
import it.unibz.inf.ontop.spec.mapping.MappingExtractor;
import it.unibz.inf.ontop.spec.dbschema.PreProcessedImplicitRelationalDBConstraintExtractor;

public class OntopMappingSQLModule extends OntopAbstractModule {


    private final OntopMappingSQLSettings settings;

    protected OntopMappingSQLModule(OntopMappingSQLConfiguration configuration) {
        super(configuration.getSettings());
        settings = configuration.getSettings();
    }

    @Override
    protected void configure() {
        bind(OntopMappingSQLSettings.class).toInstance(settings);

        bindFromSettings(SQLPPMappingFactory.class);
        bindFromSettings(SQLMappingParser.class);
        bindFromSettings(SQLPPMappingConverter.class);
        bindFromSettings(PreProcessedImplicitRelationalDBConstraintExtractor.class);
        bindFromSettings(MappingExtractor.class);

        Module nativeQLFactoryModule = buildFactory(
                ImmutableList.of(RDBMetadataExtractor.class),
                NativeQueryLanguageComponentFactory.class);
        install(nativeQLFactoryModule);
    }
}
