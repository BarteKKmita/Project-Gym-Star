package com.learning.gym.star;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.Charset;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.config.SchemaConfig;

import java.time.ZoneId;
import java.util.TimeZone;

import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.distribution.Version.v5_7_19;

public class EmbeddedMySqlProvider {

    private static EmbeddedMysql embeddedMysql;

    public static void setUpClass(){
        setupEmbeddedMySQL();
    }

    public static void tearDownClass(){
        stopAndCloseDatabase();
    }

    private static void stopAndCloseDatabase(){
        if (null != embeddedMysql) {
            embeddedMysql.stop();
        }
    }

    private static void setupEmbeddedMySQL(){
        MysqldConfig config = MysqldConfig.aMysqldConfig(v5_7_19)
                .withPort(3307)
                .withTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")))
                .withUser("test", "test")
                .build();
        SchemaConfig schemaConfig = SchemaConfig.aSchemaConfig("test_database")
                .withScripts(classPathScript(("db/schema.sql")))
                .withCharset(Charset.UTF8)
                .build();
        embeddedMysql = EmbeddedMysql.anEmbeddedMysql(config)
                .addSchema(schemaConfig)
                .start();
    }
}
