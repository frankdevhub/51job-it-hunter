package frankdevhub.job.automatic.google.drive.ftp.adapter.view.ftp;

import frankdevhub.job.automatic.core.data.logging.Logger;
import frankdevhub.job.automatic.core.data.logging.LoggerFactory;
import org.apache.ftpserver.command.AbstractCommand;
import org.apache.ftpserver.ftplet.FtpFile;
import org.apache.ftpserver.ftplet.FtpReply;
import org.apache.ftpserver.ftplet.FtpRequest;
import org.apache.ftpserver.impl.FtpIoSession;
import org.apache.ftpserver.impl.FtpServerContext;
import org.apache.ftpserver.impl.LocalizedFtpReply;
import org.apache.ftpserver.util.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * <p>Title:FtpCommands.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.frankdevhub.site</p>
 * <p>github: https://github.com/frankdevhub</p>
 *
 * @author frankdevhub
 * @date:2019-04-23 16:56
 */

public class FtpCommands {
    public static class MFMT extends AbstractCommand {

        private final Logger LOGGER = LoggerFactory.getLogger(FtpCommands.class);

        public void execute(final FtpIoSession session, final FtpServerContext context, final FtpRequest request) {
            session.resetState();

            String argument = request.getArgument();

            if (argument == null || argument.trim().length() == 0) {
                session.write(LocalizedFtpReply.translate(session, request, context,
                        FtpReply.REPLY_501_SYNTAX_ERROR_IN_PARAMETERS_OR_ARGUMENTS, "MFMT.invalid", null));
                return;
            }

            String[] arguments = argument.split(" ", 2);

            if (arguments.length != 2) {
                session.write(LocalizedFtpReply.translate(session, request, context,
                        FtpReply.REPLY_501_SYNTAX_ERROR_IN_PARAMETERS_OR_ARGUMENTS, "MFMT.invalid", null));
                return;
            }

            String timestamp = arguments[0].trim();

            try {

                Date time = DateUtils.parseFTPDate(timestamp);

                String fileName = arguments[1].trim();

                FtpFile file = null;

                try {
                    file = session.getFileSystemView().getFile(fileName);
                } catch (Exception ex) {
                    LOGGER.begin().error("Exception getting the file object: " + fileName);
                }

                if (file == null || !file.doesExist()) {
                    session.write(LocalizedFtpReply.translate(session, request, context,
                            FtpReply.REPLY_550_REQUESTED_ACTION_NOT_TAKEN, "MFMT.filemissing", fileName));
                    return;
                }

                if (!file.setLastModified(time.getTime())) {

                    session.write(LocalizedFtpReply.translate(session, request, context,
                            FtpReply.REPLY_450_REQUESTED_FILE_ACTION_NOT_TAKEN, "MFMT", fileName));
                    return;
                }

                session.write(LocalizedFtpReply.translate(session, request, context, FtpReply.REPLY_213_FILE_STATUS,
                        "MFMT", "ModifyTime=" + timestamp + "; " + fileName));

            } catch (ParseException e) {
                session.write(LocalizedFtpReply.translate(session, request, context,
                        FtpReply.REPLY_501_SYNTAX_ERROR_IN_PARAMETERS_OR_ARGUMENTS, "MFMT.invalid", null));
            }

        }
    }
}
