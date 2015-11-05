package apigee

import org.gradle.api.*

/**
 * Created by carlosfrias on 10/25/15.
 */
class ApiProxyPluginExtension {

    String hostURL
    String orgName
    String envName
    String application
    String apiVersion
    String projectVersion
    String username
    String password
    String buildDirectory
    String baseDirectory
    String revision
    String apiProxySource = 'apiproxy'
    String apiProxyEndpointProtocol = 'https'
}
