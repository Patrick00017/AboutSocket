import java.util.Vector;

public class ChatManager {
    //因为一个聊天系统只有一个聊天管理，所以需进行单例化private
    /*
     *单例化
     */
    private ChatManager() {}
    //饿汉式单例
    private static final ChatManager cm=new ChatManager();
    public static ChatManager GetChatManager() {
        return cm;
    }

    //创建保存socket的队列
    private Vector<ChatSocket> vector=new Vector<>();

    //添加聊天人
    public void AddChatPeople(ChatSocket cs) {
        vector.add(cs);
    }

    //群发消息
    public void Send(ChatSocket cs,String str) {
        for (int i = 0; i < vector.size(); i++) {
            ChatSocket chatsocket=(ChatSocket)vector.get(i);
            if(!cs.equals(chatsocket))
            {
                chatsocket.Out(str, cs.getClientName());
            }
        }
    }
}
