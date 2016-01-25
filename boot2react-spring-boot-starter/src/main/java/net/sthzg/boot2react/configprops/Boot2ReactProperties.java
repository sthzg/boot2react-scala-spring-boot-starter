package net.sthzg.boot2react.configprops;

import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties("boot2react")
public class Boot2ReactProperties {

    /** The build directory from which the app will be served (build or build-tmp). */
    private String buildDir;

    /** Protocol for the node render server (defaults to http) */
    private String protocol = "http";

    /** Host for the node render server (defaults to 127.0.0.1) */
    private String host = "127.0.0.1";

    /** Port for the node render server. */
    private Short port = 9999;

    /** The endpoint on the NodeJS render server that accepts POST requests for SSR */
    private String renderEndpoint = "api/render";


    //
    // Helpers, Computed Fields
    // –––

    public String getRenderEndpointURI() {
        return protocol + "://" + host + ":" + port + "/" + renderEndpoint;
    }


    //
    // Getters and Setters
    // –––

    public String getBuildDir() {
        return buildDir;
    }

    public void setBuildDir(String buildDir) {
        this.buildDir = buildDir;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Short getPort() {
        return port;
    }

    public void setPort(Short port) {
        this.port = port;
    }

    public String getRenderEndpoint() {
        return renderEndpoint;
    }

    public void setRenderEndpoint(String renderEndpoint) {
        this.renderEndpoint = renderEndpoint;
    }
}
