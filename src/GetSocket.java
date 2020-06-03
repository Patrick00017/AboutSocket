import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GetSocket extends Thread{

    @Override
    public void run() {
        try {
            //创建绑定到特定端口的服务器套接字  1-65535
            ServerSocket serversocket = new ServerSocket(62224);
            while(true) {
                //建立连接，获取socket对象,若没有接收到客户端进入，此语句阻塞。
                Socket socket=serversocket.accept();
                //消息提示框,请用户输入用户名
                // todo: 请用户输入用户名
                JOptionPane.showMessageDialog(null, "有客户端连接到了本机62224端口哦");
                String clientName = JOptionPane.showInputDialog("请设置用户名，方便交流：", JOptionPane.YES_OPTION);
                //与客户端通信
                ChatSocket cs=new ChatSocket(socket, clientName);
                cs.start();
                //添加socket到队列
                ChatManager.GetChatManager().AddChatPeople(cs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
