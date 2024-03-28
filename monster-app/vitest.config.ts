/**
      Copyright (c) 2024 crate.monster
*/

import { defineConfig } from 'vitest/config';
import * as path from 'node:path';

export const alias = [
  { find: 'i18n', replacement: path.resolve(__dirname, 'packages/monster-i18n/src') },
  { find: 'app', replacement: path.resolve(__dirname, 'packages/monster-remix-app/src') },
  { find: 'client', replacement: path.resolve(__dirname, 'packages/monster-scala-client/src') },
  { find: 'uikit', replacement: path.resolve(__dirname, 'packages/monster-uikit/src') }
];

export default defineConfig({
  test: {
    coverage: {
      reporter: ['text', 'lcov']
    },
    environment: 'node',
    include: ['test/**/*Test.ts'],
    pool: 'forks'
  },
  resolve: {
    alias
  }
});
