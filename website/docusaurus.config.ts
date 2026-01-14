import {themes as prismThemes} from 'prism-react-renderer';
import type {Config} from '@docusaurus/types';
import type * as Preset from '@docusaurus/preset-classic';

const config: Config = {
  title: 'THB-CC: App',
  tagline: 'A cloud computing development and operations project!',
  favicon: 'img/thb.ico',
  future: {
    v4: true,
  },
  url: 'https://thb-cc.github.io/',
  baseUrl: '/app/',
  organizationName: 'thb-cc',
  projectName: 'app',
  deploymentBranch: 'gh-pages',
  trailingSlash: false,
  onBrokenLinks: 'throw',
  i18n: {
    defaultLocale: 'en',
    locales: ['en'],
  },
  presets: [
    [
      'classic',
      {
        docs: {
          sidebarPath: './sidebars.ts',
          editUrl: 'https://github.com/thb-cc/app/tree/main/website/docs',
        },
        theme: {
          customCss: './src/css/custom.css',
        },
      } satisfies Preset.Options,
    ],
  ],
  themeConfig: {
    colorMode: {
      respectPrefersColorScheme: true,
    },
    navbar: {
      title: 'THB-CC: App',
      logo: {
        src: 'img/thb-banner.png',
      },
      items: [
        {
          type: 'docSidebar',
          sidebarId: 'sidebar',
          position: 'left',
          label: 'Docs',
        },
        {
          href: 'https://github.com/thb-cc/app',
          label: 'GitHub',
          position: 'right',
        },
      ],
    },
    footer: {
      style: 'dark',
      links: [],
      copyright: `Copyright © ${new Date().getFullYear()} My Project, Inc. Built with Docusaurus.`,
    },
    prism: {
      theme: prismThemes.github,
      darkTheme: prismThemes.dracula,
    },
  } satisfies Preset.ThemeConfig,
};

export default config;
