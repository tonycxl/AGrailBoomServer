package base
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.ChannelOption
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.socket.SocketChannel


/**
 * @Author: Xdestiny
 * @CreatedTime: 2018/4/9
 * @Descriptor:
 * @Modified:
 */
class GameServer(port: Int) {
    private val m_port = port

    fun start(){
        val bossGroup = NioEventLoopGroup() //用于处理服务器端接收客户端连接
        val workerGroup = NioEventLoopGroup() //进行网络通信（读写）

        try {
            val bootstrap = ServerBootstrap() //辅助工具类，用于服务器通道的一系列配置
            bootstrap.group(bossGroup, workerGroup) //绑定两个线程组
                    .channel(NioServerSocketChannel::class.java) //指定NIO的模式
                    .childHandler(object : ChannelInitializer<SocketChannel>() { //配置具体的数据处理方式
                        @Throws(Exception::class)
                        override fun initChannel(socketChannel: SocketChannel) {
                            socketChannel.pipeline().addLast(GameServerHandler())
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128) //设置TCP缓冲区
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024) //设置发送数据缓冲大小
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024) //设置接受数据缓冲大小
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //保持连接
            val future = bootstrap.bind(m_port).sync()
            future.channel().closeFuture().sync()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            workerGroup.shutdownGracefully()
            bossGroup.shutdownGracefully()
        }
    }
}