package base

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

/**
 * @Author: Xdestiny
 * @CreatedTime: 2018/4/9
 * @Descriptor:
 * @Modified:
 */
class GameServerHandler: io.netty.channel.ChannelInboundHandlerAdapter() {

    override fun channelRead(ctx: io.netty.channel.ChannelHandlerContext?, msg: Any?) {
        super.channelRead(ctx, msg)
    }

    override fun exceptionCaught(ctx: io.netty.channel.ChannelHandlerContext?, cause: Throwable?) {
        super.exceptionCaught(ctx, cause)
    }
}