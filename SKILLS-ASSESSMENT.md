# Skills Assessment — apigee-gradle-plugin

> **Skill domain:** Gradle plugin authoring and build/CI integration for developer-facing API proxy management — Apigee. Part of the broader Apigee platform-operations portfolio; see the [`bap_coe` portfolio hub →](https://github.com/carlosfrias/apigee-hybrid-workspace/blob/master/SKILLS-ASSESSMENT.md) for the cloud-native (Hybrid/K8s) counterpart and the full corpus.

---

## Why this project is notable

- **A published Gradle plugin.** Built with the `plugin-publish-plugin` and publishable to the Gradle Plugin Portal — not a script, a real plugin with an extension class.
- **Proxy management from the build.** Exposes api proxy management functions so proxy deploy/undeploy/import/export become Gradle tasks (`gradle tasks` lists them) — proxy lifecycle lives inside the developer build, not a separate ops step.
- **Unit-test and CI integration.** Designed to integrate with unit tests, so proxy assertions can be validated in the build before promote.
- **A different substrate.** Groovy/Gradle developer tooling alongside the Ansible operator automation — the same Apigee domain served to two audiences.

---

## Expertise demonstrated

> Groovy/Gradle is the medium. The engineering evidence lives in the [project README →](README.md). What follows is the skills assessment for the business reader.

- **Gradle plugin authoring** — a Portal-publishable plugin with a custom extension class (`ApiProxyPluginExtension`) and registered tasks. The plugin architecture (extension class + task registration) is the Gradle-idiomatic way to add domain tasks to the build.
- **Apigee API proxy management** — proxy deploy/import/export/undeploy exposed as build tasks. The developer runs `gradle tasks` and sees Apigee proxy management alongside compile and test — proxy lifecycle wired into the build.
- **Build/CI integration** — proxy lifecycle wired into the build and unit-test pipeline. Proxy assertions can be validated in the build before promote, not as a separate ops step.
- **Groovy DSL design** — the extension class configures the plugin from `build.gradle`, following the Gradle DSL convention.

---

## How this shows the expertise

This plugin is the build-tooling tier of the Apigee work. The Ansible roles handle operator-facing platform lifecycle (provision, configure, upgrade, monitor); this plugin handles developer-facing proxy lifecycle (deploy, import, export, undeploy) from the build. The same Apigee domain, two audiences, two substrates.

The expertise is not "writing Gradle tasks" — it is **designing a plugin architecture that puts proxy management inside the developer build**, so proxy lifecycle becomes part of `gradle tasks` alongside compile and test. The extension class is the Gradle-idiomatic pattern; the Portal publication is the distribution mechanism.

---

## Related expertise

| Skill | Repository | Assessment |
|-------|-----------|-----------|
| Apigee Hybrid / K8s automation (portfolio hub) | [`apigee-hybrid-workspace`](https://github.com/carlosfrias/apigee-hybrid-workspace) | [SKILLS-ASSESSMENT.md →](https://github.com/carlosfrias/apigee-hybrid-workspace/blob/master/SKILLS-ASSESSMENT.md) ✅ portfolio hub |
| Rolling upgrade / DR / traffic fencing | [`apigee-opdk-playbook-maintenance-opdk-upgrade`](https://github.com/carlosfrias/apigee-opdk-playbook-maintenance-opdk-upgrade) | [SKILLS-ASSESSMENT.md →](https://github.com/carlosfrias/apigee-opdk-playbook-maintenance-opdk-upgrade/blob/master/SKILLS-ASSESSMENT.md) ✅ |

---

## Provenance

Authored and maintained by **Carlos Frias** during his tenure on Apigee. This skills assessment is the companion to the engineering [README →](README.md). For the full engineering detail — plugin architecture, tasks, and usage — see the project README.

## License

See [LICENSE.md](./LICENSE.md).