package com.hansheng.studynote;

/**
 * Created by hansheng on 2016/7/16.
 */

import org.junit.Test;

import java.io.IOException;

import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import okio.Timeout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public final class ResponseTest {
    @Test public void peekShorterThanResponse() throws Exception {
        Response response = newResponse(responseBody("abcdef"));
        ResponseBody peekedBody = response.peekBody(3);
        assertEquals("abc", peekedBody.string());
        assertEquals("abcdef", response.body().string());
    }

    @Test public void peekLongerThanResponse() throws Exception {
        Response response = newResponse(responseBody("abc"));
        ResponseBody peekedBody = response.peekBody(6);
        assertEquals("abc", peekedBody.string());
        assertEquals("abc", response.body().string());
    }

    @Test public void peekAfterReadingResponse() throws Exception {
        Response response = newResponse(responseBody("abc"));
        assertEquals("abc", response.body().string());

        try {
            response.peekBody(3);
            fail();
        } catch (IllegalStateException expected) {
        }
    }

    @Test public void eachPeakIsIndependent() throws Exception {
        Response response = newResponse(responseBody("abcdef"));
        ResponseBody p1 = response.peekBody(4);
        ResponseBody p2 = response.peekBody(2);
        assertEquals("abcdef", response.body().string());
        assertEquals("abcd", p1.string());
        assertEquals("ab", p2.string());
    }

    /**
     * Returns a new response body that refuses to be read once it has been closed. This is true of
     * most {@link BufferedSource} instances, but not of {@link Buffer}.
     */
    private ResponseBody responseBody(String content) {
        final Buffer data = new Buffer().writeUtf8(content);

        Source source = new Source() {
            boolean closed;

            @Override public void close() throws IOException {
                closed = true;
            }

            @Override public long read(Buffer sink, long byteCount) throws IOException {
                if (closed) throw new IllegalStateException();
                return data.read(sink, byteCount);
            }

            @Override public Timeout timeout() {
                return Timeout.NONE;
            }
        };

        return ResponseBody.create(null, -1, Okio.buffer(source));
    }

    private Response newResponse(ResponseBody responseBody) {
        return new Response.Builder()
                .request(new Request.Builder()
                        .url("https://example.com/")
                        .build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .body(responseBody)
                .build();
    }
}