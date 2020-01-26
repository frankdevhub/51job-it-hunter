
#--------------------------------------------------------
#-- @author frankdevhub@gmail.com
#-- @Date 2019/04/21 Sunday
#-- @description: shutdown google drive service
#-- @host: http://localhost:4000
#--------------------------------------------------------
#!/bin/sh

echo "start"

#ps aux|grep chromedriver.exe|awk '{print $4}'|xargs kill -9

taskkill /f /im chromedriver.exe
