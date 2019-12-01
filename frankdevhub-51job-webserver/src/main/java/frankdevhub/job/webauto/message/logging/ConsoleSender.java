package frankdevhub.job.webauto.message.logging;

import frankdevhub.job.webauto.message.IMessageSender;
import frankdevhub.job.webauto.message.MessageItem;

/**
 * <p>Title:ConsoleSender.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @author frankdevhub
 * @date:2019-04-20 22:46
 */

public class ConsoleSender implements IMessageSender {

    @Override
    public void sendMessage(MessageItem source) {
        System.out.println(source.toJson());
    }
}
