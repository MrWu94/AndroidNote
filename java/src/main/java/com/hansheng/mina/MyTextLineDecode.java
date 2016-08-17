package com.hansheng.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * Created by hansheng on 2016/8/17.
 */
public class MyTextLineDecode implements ProtocolDecoder {
    @Override
    public void decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        int startPostion=ioBuffer.position();
        while (ioBuffer.hasRemaining()){
            byte b=ioBuffer.get();
            if(b=='\n'){
                int currentPosition=ioBuffer.position();
                int limit=ioBuffer.limit();
                ioBuffer.position(startPostion);
                ioBuffer.limit(currentPosition);
                IoBuffer buf=ioBuffer.slice();
                byte[] dest=new byte[buf.limit()];
                buf.get(dest);
                String str=new String(dest);
                protocolDecoderOutput.write(str);
                ioBuffer.position(currentPosition);
                ioBuffer.limit(limit);
            }
        }
    }

    @Override
    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }

    @Override
    public void dispose(IoSession ioSession) throws Exception {

    }
}
