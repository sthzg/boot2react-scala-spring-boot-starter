package net.sthzg.boot2react.configprops;

import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties("boot2react")
public class Boot2ReactProperties {

    /** The build directory from which the app will be served (build or build-tmp). */
    private String buildDir;

    /** Protocol for the node render server (defaults to http) */
    private String nodeRenderServerProtocol = "http";

    /** Host for the node render server (defaults to 127.0.0.1) */
    private String getNodeRenderServerHost = "127.0.0.1";

    /** Port for the node render server. */
    private Short nodeRenderServerPort = 9999;


    //
    // Getters and Setters
    // –––

    public String getBuildDir() {
        return buildDir;
    }

    public void setBuildDir(String buildDir) {
        this.buildDir = buildDir;
    }

    public String getNodeRenderServerProtocol() {
        return nodeRenderServerProtocol;
    }

    public void setNodeRenderServerProtocol(String nodeRenderServerProtocol) {
        this.nodeRenderServerProtocol = nodeRenderServerProtocol;
    }

    public String getGetNodeRenderServerHost() {
        return getNodeRenderServerHost;
    }

    public void setGetNodeRenderServerHost(String getNodeRenderServerHost) {
        this.getNodeRenderServerHost = getNodeRenderServerHost;
    }

    public Short getNodeRenderServerPort() {
        return nodeRenderServerPort;
    }

    public void setNodeRenderServerPort(Short nodeRenderServerPort) {
        this.nodeRenderServerPort = nodeRenderServerPort;
    }
}
