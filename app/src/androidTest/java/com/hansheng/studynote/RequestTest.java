package com.hansheng.studynote;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;

import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.Buffer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;


/**
 * Created by hansheng on 2016/7/16.
 */
public final class RequestTest {
    @Test public void string() throws Exception {
        MediaType contentType = MediaType.parse("text/plain; charset=utf-8");
        RequestBody body = RequestBody.create(contentType, "abc".getBytes(Util.UTF_8));
        assertEquals(contentType, body.contentType());
        assertEquals(3, body.contentLength());
        assertEquals("616263", bodyToHex(body));
        assertEquals("Retransmit body", "616263", bodyToHex(body));
    }

    @Test public void stringWithDefaultCharsetAdded() throws Exception {
        MediaType contentType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(contentType, "\u0800");
        assertEquals(MediaType.parse("text/plain; charset=utf-8"), body.contentType());
        assertEquals(3, body.contentLength());
        assertEquals("e0a080", bodyToHex(body));
    }

    @Test public void stringWithNonDefaultCharsetSpecified() throws Exception {
        MediaType contentType = MediaType.parse("text/plain; charset=utf-16be");
        RequestBody body = RequestBody.create(contentType, "\u0800");
        assertEquals(contentType, body.contentType());
        assertEquals(2, body.contentLength());
        assertEquals("0800", bodyToHex(body));
    }

    @Test public void byteArray() throws Exception {
        MediaType contentType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(contentType, "abc".getBytes(Util.UTF_8));
        assertEquals(contentType, body.contentType());
        assertEquals(3, body.contentLength());
        assertEquals("616263", bodyToHex(body));
        assertEquals("Retransmit body", "616263", bodyToHex(body));
    }

    @Test public void byteArrayRange() throws Exception {
        MediaType contentType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(contentType, ".abcd".getBytes(Util.UTF_8), 1, 3);
        assertEquals(contentType, body.contentType());
        assertEquals(3, body.contentLength());
        assertEquals("616263", bodyToHex(body));
        assertEquals("Retransmit body", "616263", bodyToHex(body));
    }

    @Test public void file() throws Exception {
        File file = File.createTempFile("RequestTest", "tmp");
        FileWriter writer = new FileWriter(file);
        writer.write("abc");
        writer.close();

        MediaType contentType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(contentType, file);
        assertEquals(contentType, body.contentType());
        assertEquals(3, body.contentLength());
        assertEquals("616263", bodyToHex(body));
        assertEquals("Retransmit body", "616263", bodyToHex(body));
    }

    /** Common verbs used for apis such as GitHub, AWS, and Google Cloud. */
    @Test public void crudVerbs() throws IOException {
        MediaType contentType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(contentType, "{}");

        Request get = new Request.Builder().url("http://localhost/api").get().build();
        assertEquals("GET", get.method());
        assertNull(get.body());

        Request head = new Request.Builder().url("http://localhost/api").head().build();
        assertEquals("HEAD", head.method());
        assertNull(head.body());

        Request delete = new Request.Builder().url("http://localhost/api").delete().build();
        assertEquals("DELETE", delete.method());
        assertEquals(0L, delete.body().contentLength());

        Request post = new Request.Builder().url("http://localhost/api").post(body).build();
        assertEquals("POST", post.method());
        assertEquals(body, post.body());

        Request put = new Request.Builder().url("http://localhost/api").put(body).build();
        assertEquals("PUT", put.method());
        assertEquals(body, put.body());

        Request patch = new Request.Builder().url("http://localhost/api").patch(body).build();
        assertEquals("PATCH", patch.method());
        assertEquals(body, patch.body());
    }

    @Test public void uninitializedURI() throws Exception {
        Request request = new Request.Builder().url("http://localhost/api").build();
        assertEquals(new URI("http://localhost/api"), request.url().uri());
        assertEquals(HttpUrl.parse("http://localhost/api"), request.url());
    }

    @Test public void newBuilderUrlResetsUrl() throws Exception {
        Request requestWithoutCache = new Request.Builder().url("http://localhost/api").build();
        Request builtRequestWithoutCache =
                requestWithoutCache.newBuilder().url("http://localhost/api/foo").build();
        assertEquals(HttpUrl.parse("http://localhost/api/foo"), builtRequestWithoutCache.url());

        Request requestWithCache = new Request.Builder().url("http://localhost/api").build();
        // cache url object
        requestWithCache.url();
        Request builtRequestWithCache = requestWithCache.newBuilder().url(
                "http://localhost/api/foo").build();
        assertEquals(HttpUrl.parse("http://localhost/api/foo"), builtRequestWithCache.url());
    }

    @Test public void cacheControl() throws Exception {
        Request request = new Request.Builder()
                .cacheControl(new CacheControl.Builder().noCache().build())
                .url("https://square.com")
                .build();
        assertEquals(Arrays.asList("no-cache"), request.headers("Cache-Control"));
    }

    @Test public void emptyCacheControlClearsAllCacheControlHeaders() throws Exception {
        Request request = new Request.Builder()
                .header("Cache-Control", "foo")
                .cacheControl(new CacheControl.Builder().build())
                .url("https://square.com")
                .build();
        assertEquals(Collections.<String>emptyList(), request.headers("Cache-Control"));
    }

    @Test public void headerAcceptsPermittedCharacters() throws Exception {
        Request.Builder builder = new Request.Builder();
        builder.header("AZab09 ~", "AZab09 ~");
        builder.addHeader("AZab09 ~", "AZab09 ~");
    }

    @Test public void emptyNameForbidden() throws Exception {
        Request.Builder builder = new Request.Builder();
        try {
            builder.header("", "Value");
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            builder.addHeader("", "Value");
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    @Test public void headerForbidsNullArguments() throws Exception {
        Request.Builder builder = new Request.Builder();
        try {
            builder.header(null, "Value");
            fail();
        } catch (NullPointerException expected) {
        }
        try {
            builder.addHeader(null, "Value");
            fail();
        } catch (NullPointerException expected) {
        }
        try {
            builder.header("Name", null);
            fail();
        } catch (NullPointerException expected) {
        }
        try {
            builder.addHeader("Name", null);
            fail();
        } catch (NullPointerException expected) {
        }
    }

    @Test public void headerForbidsControlCharacters() throws Exception {
        assertForbiddenHeader("\u0000");
        assertForbiddenHeader("\r");
        assertForbiddenHeader("\n");
        assertForbiddenHeader("\t");
        assertForbiddenHeader("\u001f");
        assertForbiddenHeader("\u007f");
        assertForbiddenHeader("\u0080");
        assertForbiddenHeader("\ud83c\udf69");
    }

    private void assertForbiddenHeader(String s) {
        Request.Builder builder = new Request.Builder();
        try {
            builder.header(s, "Value");
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            builder.addHeader(s, "Value");
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            builder.header("Name", s);
            fail();
        } catch (IllegalArgumentException expected) {
        }
        try {
            builder.addHeader("Name", s);
            fail();
        } catch (IllegalArgumentException expected) {
        }
    }

    private String bodyToHex(RequestBody body) throws IOException {
        Buffer buffer = new Buffer();
        body.writeTo(buffer);
        return buffer.readByteString().hex();
    }
}