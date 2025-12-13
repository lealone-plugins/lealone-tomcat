/*
 * Copyright Lealone Database Group.
 * Licensed under the Server Side Public License, v 1.
 * Initial Developer: zhh
 */
package com.lealone.plugins.tomcat;

import org.apache.tomcat.util.net.NioChannel;
import org.apache.tomcat.util.net.SocketEvent;
import org.apache.tomcat.util.net.SocketProcessorBase;
import org.apache.tomcat.util.net.SocketWrapperBase;

import com.lealone.db.scheduler.Scheduler;
import com.lealone.net.NetBuffer;
import com.lealone.net.TransferConnection;
import com.lealone.net.WritableChannel;

public class TomcatServerConnection extends TransferConnection {

    // private final HttpServer httpServer;
    // private final Scheduler scheduler;

    private final SocketProcessorBase<NioChannel> socketProcessor;

    public TomcatServerConnection(TomcatServer httpServer, WritableChannel channel,
            Scheduler scheduler) {
        super(channel, true);
        // this.httpServer = httpServer;
        // this.scheduler = scheduler;
        // LinkedList<NioChannel> nioChannels = httpServer.getNioChannels(scheduler.getHandlerId());
        TomcatNioEndpoint endpoint = (TomcatNioEndpoint) (httpServer.getProtocolHandler().getEndpoint());
        endpoint.setSelector(scheduler.getSelector());
        SocketWrapperBase<NioChannel> socketWrapper = endpoint
                .createSocketWrapper(writableChannel.getSocketChannel(), null);
        // socketWrapper.setRecycledProcessors(httpServer.getRecycledProcessors(scheduler.getHandlerId()));
        // socketWrapper.setNioChannels(nioChannels);
        socketProcessor = endpoint.createSocketProcessor(socketWrapper, SocketEvent.OPEN_READ);
        // socketWrapper.setSocketProcessor(socketProcessor);
    }

    @Override
    public int getPacketLengthByteCount() {
        return -1;
    }

    @Override
    public void handle(NetBuffer buffer, boolean autoRecycle) {
        socketProcessor.run();
    }
}
