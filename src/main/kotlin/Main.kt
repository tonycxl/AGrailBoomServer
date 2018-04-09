/**
 * @Author: Xdestiny
 * @CreatedTime: 2018/4/9
 * @Descriptor:
 * @Modified:
 */

import org.apache.commons.configuration2.builder.fluent.Configurations
import org.apache.logging.log4j.LogManager

class Main{
    companion object{
        @JvmStatic fun main(args: Array<String>) {
            LogManager.getLogger(javaClass.name).info("Read configuration...")
            val configs = Configurations()
            var config =configs.xml("config.xml")

            while (true){ }
        }
    }
}