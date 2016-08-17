package com.hansheng.mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * Created by hansheng on 2016/8/17.
 */
public class MyTextLineCodecFactory implements ProtocolCodecFactory {

    private MyTextLineEncoder myTextLineEncoder;
    private MyTextLineDecode myTextLineDecode;


    public MyTextLineCodecFactory() {
        myTextLineEncoder=new MyTextLineEncoder();
        myTextLineDecode=new MyTextLineDecode();
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return null;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return null;
    }
}
