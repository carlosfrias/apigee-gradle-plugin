# apigee-gradle-plugin — Apigee API Proxy Management for Gradle

> **A Gradle plugin that exposes Apigee API proxy management functions to the build** — deploy and manage proxies from the command line, integrate proxy lifecycle with unit tests, and wire Apigee into CI. Published to the Gradle Plugin Portal.

> [!NOTE]
> Engineering portfolio note — this project demonstrates Gradle plugin authoring and build/CI integration for developer-facing API proxy management. See the [skills assessment →](SKILLS-ASSESSMENT.md) for the expertise applied.

This is the build-tooling tier of the Apigee work — a different substrate from the Ansible corpus: **Groovy + Gradle plugin authoring** for developer-facing proxy management, where the Ansible roles handle operator-facing platform lifecycle.

---

## What the plugin provides

- `ApiProxyPlugin.groovy` — the plugin, registers the proxy-management tasks.
- `ApiProxyPluginExtension.groovy` — the extension class for configuration (`build.gradle` DSL).
- Proxy management tasks (run `gradle tasks` for the full listing with descriptions): api access from the command line for configured proxies; integrates with unit tests and CI.

---

## Usage

```groovy
// build.gradle
plugins {
    id 'apigee.api-proxy' version '<version>'
}

apigee {
    // configuration via the extension class — see ApiProxyPluginExtension
}
```

```bash
gradle tasks        # list the proxy management tasks with descriptions
```

---

## Provenance

Authored and maintained by **Carlos Frias** during his tenure on Apigee — the build-tooling tier of the Apigee work, complementing the operator-facing [`apigee-edge-opdk`](https://github.com/carlosfrias/apigee-edge-opdk) framework.

## License

See [LICENSE.md](./LICENSE.md).