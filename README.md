# Crate Monster

## Development 

Install [asdf](https://asdf-vm.com/guide/getting-started.html) with all the dependencies

```bash
asdf plugin add sbt
asdf plugin add java
asdf plugin add nodejs
asdf plugin add yarn 

asdf install
```

Both VSCode [Metals](https://scalameta.org/metals/docs/editors/vscode) and IntelliJ Idea [Scala](https://www.jetbrains.com/help/idea/get-started-with-scala.html) extension should work just fine.

All sbt tasks are scripted in turborepo, so running the following npm scripts is enough

```bash
cd monster-app
yarn build
yarn test
yarn watch
```