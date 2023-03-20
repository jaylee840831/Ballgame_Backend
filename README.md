# Ballgame_Backend

## 啟動專案

** 注意事項:<br/> 
專案內的資料庫來源請設定為自己的資料庫，路徑 src/main/resources/application.yml<br/>
驗證使用權限的 jwt token 密鑰也請自行設定 路徑 src/main/java/com/example/ballgame/service/JwtService.java

方法一 執行jar檔<br/>

開啟命令提示字元，進入專案根目錄，輸入以下指令<br/>
    
    java -jar Ballgame_Backend-0.0.1-SNAPSHOT.jar

方法二 專案匯入Eclipse、IntelliJ等開發工具後執行

## 開發工具

1. Spring Boot
2. Java 17
3. Eclipse

## 資料庫schemas

_user<br/>
<img src="https://github.com/jaylee840831/Ball-Meet/blob/master/image/user-schema.jpg" />

_user_ball_game<br/>
<img src="https://github.com/jaylee840831/Ball-Meet/blob/master/image/user-ball-game-schema.jpg" />

ball_game<br/>
<img src="https://github.com/jaylee840831/Ball-Meet/blob/master/image/ballgame-schema.jpg" />

chat_room<br/>
<img src="https://github.com/jaylee840831/Ball-Meet/blob/master/image/chat-room-schema.jpg" />

token<br/>
<img src="https://github.com/jaylee840831/Ball-Meet/blob/master/image/token-schema.jpg" />

user_info<br/>
<img src="https://github.com/jaylee840831/Ball-Meet/blob/master/image/user-info-schema.jpg" />