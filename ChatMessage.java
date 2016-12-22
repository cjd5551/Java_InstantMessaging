
import java.io.Serializable;


public class ChatMessage implements Serializable
    {
        protected static final long serialVersionUID = 1112122200L;

        static final int who_is_in = 0, message = 1, logout = 2;
        private int type;
        private String message_str;
        
        public ChatMessage(int type, String msg)
            {
                this.type = type;
                this.message_str = msg;
            }
        
        int getType()
            { return type; }
        
        String getMessage()
            { return message_str; }
    }
