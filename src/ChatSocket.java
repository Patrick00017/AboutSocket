import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatSocket extends Thread{

    Socket socket;
    String clientName = "";
    public ChatSocket(Socket socket, String clientName) {
        this.socket=socket;
        this.clientName = clientName;
    }

    /*
     *向客户端输出信息
     */
    public void Out(String str, String name) {
        try {
            if (str.equals("success\n")){
                socket.getOutputStream().write(str.getBytes("UTF-8"));
            }
            else {
                String out = name + ": " + str;
                socket.getOutputStream().write(out.getBytes("UTF-8"));
            }
            socket.getOutputStream().write("\r\n".getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Out("success\n", "");
        //成功连接进服务器
        ChatManager.GetChatManager().Send(this, "connect to the service.");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));

            String line=null;
            while((line=br.readLine())!=null)
            {
                System.out.println(line);
                ChatManager.GetChatManager().Send(this, line);
            }

            br.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClientName() {
        return clientName;
    }
}
