package apigee

import org.gradle.api.Plugin
import org.gradle.api.Project
import wslite.rest.Response

/**
 * Created by carlosfrias on 10/25/15.
 */
public class ApiProxyPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.extensions.create('apiproxy', ApiProxyPluginExtension)

        project.task('lastRevision') {
            group 'Api Proxy Execution Manual Service Invocations'
            description 'Retrieve the last revision number.'

            doLast {
                def apigee = new Apigee(profile: getApigeeProfile(project))
                def apiProxy = apigee.apiProxy
                println "Api Proxy Last Revision for ${apigee.profile.organizationURL}${apigee.profile.apiProxyURL}"
                println "Api Proxy Last Revision: ${apiProxy.lastRevision}"
            }
        }
        project.task('createApiProxy') {
            group 'Api Proxy Execution Manual Service Invocations'
            description ''' Creates an API Proxy.The API proxy that is created using this call will
                            not be accessible at runtime until the proxy is deployed to an environment.'''

            doLast {
                def apigee = new Apigee(profile: getApigeeProfile(project))
                def proxy = apigee.apiProxy
                println "Create Api Proxy for ${apigee.profile.organizationURL}${project.apiproxy.application}"
                def resp = proxy.createApiProxy
                reportServerResponse(resp)
            }
        }
        project.task('deleteApiProxy') {
            group 'Api Proxy Execution Manual Service Invocations'
            description ''' Deletes an API proxy and all associated endpoints, policies, resources,
                            and revisions. The API proxy must be undeployed before you
                            can delete it.'''

            doLast {
                def apigee = new Apigee(profile: getApigeeProfile(project))
                def proxy = apigee.apiProxy
                println "Delete Api Proxy for ${apigee.profile.organizationURL}${apigee.profile.application}"
                def resp = proxy.deleteApiProxy
                reportServerResponse(resp)
            }
        }
        project.task('deleteApiProxyRevision') {
            group 'Api Proxy Execution Manual Service Invocations'
            description ''' Deletes a revision of an API proxy and all policies, resources, endpoints,
                            and revisions associated with it. The API proxy revision must be undeployed
                            before you can delete it.'''

            doLast {
                def apigee = new Apigee(profile: getApigeeProfile(project))
                def proxy = apigee.apiProxy
                println "Delete Api Proxy Revision for ${apigee.profile.organizationURL}${apigee.profile.apiProxyRevisionURL}"
                def resp = proxy.deleteApiProxyRevision
                reportServerResponse(resp)
            }
        }
        project.task('exportApiProxy') {
            group 'Api Proxy Execution Manual Service Invocations'
            description ''' Outputs an API proxy revision as a ZIP formatted bundle of code and config
                            files. This enables local configuration and development, including attachment
                            of policies and scripts.'''

            doLast {
                def apigee = new Apigee(profile: getApigeeProfile(project))
                def proxy = apigee.apiProxy
                println "Export Api Proxy for ${apigee.profile.organizationURL}${apigee.profile.apiProxyRevisionURL}"
                def resp = proxy.exportApiProxy()
                reportServerResponse(resp)
            }
        }
        project.task('extractExportApiProxy') {
            group 'Api Proxy Execution Manual Service Invocations'
            description 'Unzip the archive downloaded with exportApiProxy'

            doLast {
                def apigee = new Apigee(profile: getApigeeProfile(project))
                def proxy = apigee.apiProxy
                println "Extracted Api Proxy for ${apigee.profile.organizationURL}${apigee.profile.apiProxyRevisionURL}"
                def resp = proxy.extractExportApiProxy
                println "Exported Api Proxy Extracted to: \n${resp}"
            }
        }
        project.task('apiProxy') {
            group 'Api Proxy Execution Manual Service Invocations'
            description 'Gets an API proxy by name, including a list of existing revisions of the proxy.'

            doLast {
                def apigee = new Apigee(profile: getApigeeProfile(project))
                def proxy = apigee.apiProxy
                println "Api Proxy Listing for ${apigee.profile.organizationURL}${apigee.profile.apiProxyURL}"
                def resp = proxy.apiProxy
                reportServerResponse(resp)
            }
        }
        project.task('listApiProxies') {
            group 'Api Proxy Execution Manual Service Invocations'
            description 'Gets the names of all API proxies in an organization. The names correspond to the names ' +
                    'defined in the configuration files for each API proxy.'

            doLast {
                def apigee = new Apigee(profile: getApigeeProfile(project))
                def proxy = apigee.apiProxy
                println "List of Api Proxies for ${apigee.profile.organizationApiURL}"
                def resp = proxy.listApiProxies
                reportServerResponse(resp)
            }
        }
        project.task('apiProxyRevision') {
            group 'Api Proxy Execution Manual Service Invocations'
            description 'Gets a specific revision of an API proxy.'

            doLast {
                def apigee = new Apigee(profile: getApigeeProfile(project))
                def proxy = apigee.apiProxy
                println "Api Proxy Revision ${apigee.profile.revision} for ${project.apiproxy.application}"
                def resp = proxy.apiProxyRevision
                reportServerResponse(resp)
            }
        }
        project.task('deployApiProxy') {
            group 'Api Proxy Execution Manual Service Invocations'
            description ''' Deploys the revision of an API proxy to an environment in an organization indicated in the
                            build script. '''

            doLast {
                def apigee = new Apigee(profile: getApigeeProfile(project))
                def proxy = apigee.apiProxy
                println "Deploy Api Proxy for ${project.apiproxy.application}"
                def resp = proxy.deployApiProxy
                reportServerResponse(resp)
            }
        }

        project.task('deployLastApiProxyRevision') {
            group 'Api Proxy Execution Manual Service Invocations'
            description ''' Deploys the last revision of an API proxy to an environment in an organization.
                            This task disregards the revision indicated in the build script and deploys the last
                            revision deployed.'''

            doLast {
                def apigee = new Apigee(profile: getApigeeProfile(project))
                def proxy = apigee.apiProxy
                proxy.unDeployAllApiProxyRevisions
                proxy.lastRevision
                println "Deploy Api Proxy Revision ${apigee.profile.revision} for ${project.apiproxy.application}"
                def resp = proxy.deployApiProxy
                reportServerResponse(resp)
            }
        }

        project.task('unDeployApiProxy') {
            group 'Api Proxy Execution Manual Service Invocations'
            description 'Undeploys all api proxy revisions from an environment.'

            doLast {
                def apigee = new Apigee(profile: getApigeeProfile(project))
                def proxy = apigee.apiProxy
                println "Undeploy All Api Proxy Revisions for ${project.apiproxy.application}"
                def apiProxyResp = proxy.unDeployAllApiProxyRevisions
                println "Undeployed All Api Proxy Revisions."
            }
        }

        project.task('createApiProxyZip') {
            group 'Api Proxy Execution Manual Service Invocations'
            description 'Undeploys an API proxy revision from an environment.'

            doLast {
                def apigee = new Apigee(profile: getApigeeProfile(project))
                def proxy = apigee.apiProxy
                println "Create Api Proxy Zip for ${apigee.profile.apiProxyZipFilename}"
                def resp = proxy.createApiProxyZip()
                println "Api Proxy Zip Created to: $resp"
            }
        }
        project.task('importApiProxy') {
            group 'Api Proxy Execution Manual Service Invocations'
            description ''' Uploads a ZIP-formatted API proxy configuration bundle from a local machine to
                            an organization on Edge. If the API proxy already exists, then create a new
                            revision of the API proxy. If the API proxy does not exist, then create it.'''

            doLast {
                def apigee = new Apigee(profile: getApigeeProfile(project))
                def proxy = apigee.apiProxy
                proxy.createApiProxyZip()
                println "Import Api Proxy for ${project.apiproxy.application}"
                def resp = proxy.importApiProxy()
                reportServerResponse(resp)
            }
        }

        project.task("downloadAllRevisions") {
            group 'Api Proxy Execution Manual Service Invocations'
            description 'Download all revisions of an api proxy into versioned directories'

            doLast {
                def apigee = new Apigee(profile: getApigeeProfile(project))
                def proxy = apigee.apiProxy
                proxy.exportAllApiProxyRevisions
            }
        }

        project.task('redeployApiProxy') {
            group 'Api Proxy Execution Manual Service Invocations'
            description 'Refresh the Api Proxy deployment from disk'

            doLast {
                def apigee = new Apigee(profile: getApigeeProfile(project))
                def proxy = apigee.apiProxy
                println "Reploying Api Proxy ${apigee.profile.application}"
                reportServerResponse proxy.redeployApiProxy()
                println "Api Proxy Deployment Process Complete."
            }
        }

    }

    def reportServerResponse(resp) {
        if (resp instanceof Response && resp.contentType.contains('json')) {
            println "Server Response:\n${resp.json.toString(2)}"
        } else {
            println "${resp}"
        }
    }

    def getApigeeProfile(project) {
        def profile = new ApigeeProfile()
        project.apiproxy.with {
            profile.hostURL = hostURL
            profile.orgName = orgName
            profile.envName = envName
            profile.application = application
            profile.apiVersion = apiVersion
            profile.projectVersion = projectVersion
            profile.username = username
            profile.password = password
            profile.buildDirectory = buildDirectory
            profile.baseDirectory = baseDirectory
            profile.apiProxySource = apiProxySource
            profile.revision = revision
        }
        return profile
    }

}
