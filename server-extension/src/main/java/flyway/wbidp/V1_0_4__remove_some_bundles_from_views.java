package flyway.wbidp;

import org.oskari.helpers.AppSetupHelper;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Connection;
import java.util.List;

/**
 * Remove some unused bundles from views.
 * These might be added back later.
 */
public class V1_0_4__remove_some_bundles_from_views extends BaseJavaMigration {
        private static final String[] BUNDLE_IDS = {"search", "statsgrid", "heatmap", "timeseries", "metadatacataloque"};

        public void migrate(Context context) throws Exception {
            Connection connection = context.getConnection();
            final List<Long> views = AppSetupHelper.getSetupsForUserAndDefaultType(connection);
            for( int i = 0; i <= BUNDLE_IDS.length - 1; i++) {
                for (Long viewId : views) {
                    if (AppSetupHelper.appContainsBundle(connection, viewId, BUNDLE_IDS[i])) {
                        AppSetupHelper.removeBundleFromApp(connection, viewId, BUNDLE_IDS[i]);
                    }
                }
            }
        }
    }
