/**
      Copyright (c) 2024 Crate Monster

      This Source Code Form is subject to the terms of the GNU AFFERO GENERAL PUBLIC LICENSE Version 3 or later
*/

export default {
  locales: ['en'],
  sourceLocale: 'en',
  catalogs: [
    {
      path: 'src/locales/{locale}/messages',
      include: ['src']
    }
  ],
  format: 'po'
};
