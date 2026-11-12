package com.sky.task;

import com.sky.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class WebSocketTask {
    @Autowired
    private WebSocketServer webSocketServer;

    /**
     * 通过WebSocket每隔5秒向客户端发送消息
     * 注意：管理端收到任何消息都会先执行 audioVo.currentTime = 0 再 JSON.parse，
     * 所以这条纯文本心跳会造成两个问题：
     *   1) 正在播报的语音被每 5 秒拉回开头，永远播不完（听感就是"没有播报/播报断断续续"）
     *   2) JSON.parse("这是来自服务端的消息：xx") 抛异常，管理端控制台每 5 秒一条红字
     * 因此联调阶段用来验证连接可以临时打开，验证完请保持注释。
     */

//    @Scheduled(cron = "0/5 * * * * ?")
    public void sendMessageToClient() {
        webSocketServer.sendToAllClient("这是来自服务端的消息：" + DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
    }
}
