package com.hansheng.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * Created by hansheng on 2016/8/17.
 */
public class MyTextLineEncoder implements ProtocolEncoder {

    @Override
    public void encode(IoSession ioSession, Object message, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        String s = null;
        if (message instanceof String) {
            s = (String) message;
        }
        if (s != null) {
            CharsetEncoder charsetEncoder = (CharsetEncoder) ioSession.getAttribute("encoder");
            if (charsetEncoder == null) {
                charsetEncoder = Charset.defaultCharset().newEncoder();
                ioSession.setAttribute("encoder", charsetEncoder);

            }
            IoBuffer ioBuffer = IoBuffer.allocate(s.length());
            ioBuffer.setAutoExpand(true);
            ioBuffer.putString(s, charsetEncoder);
            ioBuffer.flip();
            protocolEncoderOutput.write(ioBuffer);
        }
    }

    @Override
    public void dispose(IoSession ioSession) throws Exception {

    }
}
