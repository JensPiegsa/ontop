package it.unibz.inf.ontop.owlrefplatform.injection.impl;


import com.google.inject.Module;
import it.unibz.inf.ontop.injection.InvalidOBDAConfigurationException;
import it.unibz.inf.ontop.injection.impl.OBDACoreConfigurationImpl;
import it.unibz.inf.ontop.model.DataSourceMetadata;
import it.unibz.inf.ontop.owlrefplatform.core.QuestConstants;
import it.unibz.inf.ontop.owlrefplatform.core.mappingprocessing.TMappingExclusionConfig;
import it.unibz.inf.ontop.owlrefplatform.injection.QuestCoreConfiguration;
import it.unibz.inf.ontop.owlrefplatform.injection.QuestCorePreferences;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;

public class QuestCoreConfigurationImpl extends OBDACoreConfigurationImpl implements QuestCoreConfiguration {

    private final QuestCorePreferences preferences;
    private final QuestCoreOptions options;

    protected QuestCoreConfigurationImpl(QuestCorePreferences preferences, OBDAConfigurationOptions obdaOptions,
                                         QuestCoreOptions options) {
        super(preferences, obdaOptions);
        this.preferences = preferences;
        this.options = options;
    }

    /**
     * TODO: complete
     */
    @Override
    public void validate() throws InvalidOBDAConfigurationException {
//        boolean areMappingsDefined =
//                contains(OBDAProperties.MAPPING_FILE_OBJECT)
//                        || contains(OBDAProperties.MAPPING_FILE_READER)
//                        || contains(OBDAProperties.MAPPING_FILE_MODEL)
//                        || contains(OBDAProperties.MAPPING_FILE_PATH)
//                        || contains(OBDAProperties.PREDEFINED_OBDA_MODEL);
//        if ((!areMappingsDefined) && get(QuestPreferences.ABOX_MODE).equals(QuestConstants.VIRTUAL)) {
//            throw new InvalidOBDAConfigurationException("mappings are not specified in virtual mode", this);
//        } else if (areMappingsDefined && get(QuestPreferences.ABOX_MODE).equals(QuestConstants.CLASSIC)) {
//            throw new InvalidOBDAConfigurationException("mappings are specified in classic mode", this);
//        }
        /**
         * TODO: complete
         */

        // TODO: check the types of some Object properties.
    }

    @Override
    public Optional<TMappingExclusionConfig> getTmappingExclusions() {
        return options.excludeFromTMappings;
    }

    @Override
    public Optional<DataSourceMetadata> getDatasourceMetadata() {
        return options.dbMetadata;
    }

    @Override
    public QuestCorePreferences getPreferences() {
        return preferences;
    }

    @Override
    protected Stream<Module> buildGuiceModules() {
        return Stream.concat(
                super.buildGuiceModules(),
                Stream.of(new QuestComponentModule(this)));
    }

    public static class QuestCoreOptions {
        public final Optional<TMappingExclusionConfig> excludeFromTMappings;
        public final Optional<DataSourceMetadata> dbMetadata;


        public QuestCoreOptions(Optional<TMappingExclusionConfig> excludeFromTMappings,
                                Optional<DataSourceMetadata> dbMetadata) {
            this.excludeFromTMappings = excludeFromTMappings;
            this.dbMetadata = dbMetadata;
        }
    }


    public static class BuilderImpl<B extends QuestCoreConfiguration.Builder,
                                    P extends QuestCorePreferences,
                                    C extends QuestCoreConfiguration>
            extends OBDACoreConfigurationImpl.BuilderImpl<B,P,C>
            implements QuestCoreConfiguration.Builder<B> {

        private Optional<TMappingExclusionConfig> excludeFromTMappings = Optional.empty();

        private Optional<Boolean> queryingAnnotationsInOntology = Optional.empty();
        private Optional<Boolean> sameAsMappings = Optional.empty();
        private Optional<DataSourceMetadata> dbMetadata = Optional.empty();

        private boolean inVirtualMode = true;

        public BuilderImpl() {
        }

        @Override
        public B tMappingExclusionConfig(@Nonnull TMappingExclusionConfig config) {
            this.excludeFromTMappings = Optional.of(config);
            return (B) this;
        }

        @Override
        public B dbMetadata(@Nonnull DataSourceMetadata dbMetadata) {
            this.dbMetadata = Optional.of(dbMetadata);
            return (B) this;
        }

        @Override
        public B enableOntologyAnnotationQuerying(boolean queryingAnnotationsInOntology) {
            this.queryingAnnotationsInOntology = Optional.of(queryingAnnotationsInOntology);
            return (B) this;
        }

        @Override
        public B sameAsMappings(boolean sameAsMappings) {
            this.sameAsMappings = Optional.of(sameAsMappings);
            return (B) this;
        }

        @Override
        public B enableClassicABoxMode() {
            this.inVirtualMode = false;
            return (B) this;
        }

        /**
         * TODO: explain
         * TODO: find a better term
         *
         * Can be overloaded (for extensions)
         */
        @Override
        protected Properties generateProperties() {
            Properties p = super.generateProperties();
            if (!inVirtualMode) {
                p.put(QuestCorePreferences.ABOX_MODE, QuestConstants.CLASSIC);
            }

            queryingAnnotationsInOntology.ifPresent(b -> p.put(QuestCorePreferences.ANNOTATIONS_IN_ONTO, b));
            sameAsMappings.ifPresent(b -> p.put(QuestCorePreferences.SAME_AS, b));

            return p;
        }

        /**
         * Default implementation for P == QuestCorePreferences
         */
        @Override
        protected P createOBDAProperties(Properties p) {
            return (P) new QuestCorePreferencesImpl(p, isR2rml());
        }

        /**
         * Default implementation for P == QuestCorePreferences
         */
        @Override
        protected C createConfiguration(P questPreferences) {
            return (C) new QuestCoreConfigurationImpl(questPreferences, createOBDAConfigurationArguments(),
                    createQuestCoreArguments());
        }

        protected final QuestCoreOptions createQuestCoreArguments() {
            return new QuestCoreOptions(excludeFromTMappings, dbMetadata);
        }
    }
}
