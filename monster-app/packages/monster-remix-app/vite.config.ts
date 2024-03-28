/**
      Copyright (c) 2024 crate.monster
*/

import { vitePlugin as remix, cloudflareDevProxyVitePlugin as remixCloudflareDevProxy } from '@remix-run/dev';
import { defineConfig } from 'vite';
import tsconfigPaths from 'vite-tsconfig-paths';

export default defineConfig(({ mode }) => ({
  plugins: [
    remixCloudflareDevProxy(),
    remix({
      presets: []
    }),
    tsconfigPaths()
  ],
  server: {
    hmr: {
      protocol: 'ws',
      clientPort: 3000
    }
  },
  build: {
    minify: mode === 'production' ? 'terser' : false,
    sourcemap: mode !== 'production' ? 'inline' : false,
    target: 'es2022',
    emptyOutDir: false,
    terserOptions: {
      format: {
        comments: false
      }
    },
    esbuild: { legalComments: 'none' }
  }
}));
