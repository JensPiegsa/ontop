package it.unibz.inf.ontop.owlrefplatform.injection;

import it.unibz.inf.ontop.injection.OBDACoreConfiguration;
import it.unibz.inf.ontop.model.DataSourceMetadata;
import it.unibz.inf.ontop.owlrefplatform.core.mappingprocessing.TMappingExclusionConfig;
import it.unibz.inf.ontop.owlrefplatform.injection.impl.QuestCoreConfigurationImpl;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * TODO: explain
 */
public interface QuestCoreConfiguration extends OBDACoreConfiguration {

    Optional<TMappingExclusionConfig> getTmappingExclusions();

    Optional<DataSourceMetadata> getDatasourceMetadata();

    QuestCorePreferences getPreferences();


    static Builder<Builder<Builder<Builder<Builder<Builder<Builder<Builder<Builder<Builder<Builder<Builder<Builder>>>>>>>>>>>> defaultBuilder() {
        return new QuestCoreConfigurationImpl.BuilderImpl<>();
    }


    interface Builder<B extends Builder> extends OBDACoreConfiguration.Builder<B> {

        B tMappingExclusionConfig(@Nonnull TMappingExclusionConfig config);

        B dbMetadata(@Nonnull DataSourceMetadata dbMetadata);

        B enableOntologyAnnotationQuerying(boolean queryingAnnotationsInOntology);

        B sameAsMappings(boolean sameAsMappings);

        /**
         * By default, the virtual A-box mode is used.
         *
         * The classic A-box mode is not intended to be used by end-users (but for test purposes),
         * so its activation must be made explicit.
         *
         */
        B enableClassicABoxMode();

        QuestCoreConfiguration build();
    }

}
