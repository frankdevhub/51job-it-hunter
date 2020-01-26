
#--------------------------------------------------------
#-- @author frankdevhub@gmail.com
#-- @Date 2019/06/5 Sunday
#-- @description: shutdown google drive service
#-- @host: http://localhost:4000
#--------------------------------------------------------
#!/bin/sh

echo "start"


mvn clean

mvn compile

mvn package
