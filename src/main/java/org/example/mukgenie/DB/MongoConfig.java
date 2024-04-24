package org.example.mukgenie.DB;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;

public class MongoConfig {
    public static void main(String[] args) {
        // 환경 변수에서 아이디와 비밀번호 가져오기
        String username = System.getenv("MONGODB_USERNAME_ENV_VAR");
        String password = System.getenv("MONGODB_PASSWORD_ENV_VAR");

        // MongoDB 연결 URL 설정
        String connectionString = String.format("mongodb+srv://%s:%s@mukdb.acy0wqq.mongodb.net/?retryWrites=true&w=majority",
                username, password);

        // MongoDB 서버 API 설정
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        // MongoDB 연결 설정 생성
        ConnectionString connString = new ConnectionString(connectionString);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .applicationName("MukDB")
                .build();

        // MongoDB 클라이언트 생성
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            // 연결이 성공하면 성공 메시지 출력
            System.out.println("MongoDB conn.");
        } catch (Exception e) {
            // 연결에 실패하면 오류 메시지 출력
            System.err.println("MongoDB not conn: " + e.getMessage());
        }

    }
}
