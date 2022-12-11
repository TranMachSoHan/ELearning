/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    fontFamily: {
      'sans': ['Poppins', 'sans-serif']
    },
    fontSize: {
      'colossus-140': ['140px', {lineHeight:'144px'}],
      'uber-112': ['112px', {lineHeight:'120px'}],
      'hero-80': ['80px', {lineHeight:'96px'}],
      'display-64': ['64px', {lineHeight:'80px'}],
      'headline-48': ['48px', {lineHeight:'60px'}],
      'headline-37': ['37px', {lineHeight:'44px'}],
      'headline-31': ['31px', {lineHeight:'48px'}],
      'headline-26': ['26px', {lineHeight:'40px'}],
      'headline-21': ['21px', {lineHeight:'32px'}],
      'lead-24': ['24px', {lineHeight:'36px'}],
      'body-18': ['18px', {lineHeight:'32px'}],
      'small-16': ['16px', {lineHeight:'28px'}],
      'caption-14': ['14px', {lineHeight:'20px'}],
      'XSmall-12': ['12px', {lineHeight:'20px'}],
      'tiny-10': ['10px', {lineHeight:'16px'}],
      'input-18': ['18px', {lineHeight:'28px'}],
      'input-16': ['16px', {lineHeight:'24px'}],
      'button-21': ['21px', {lineHeight:'32px'}],
      'button-16': ['16px', {lineHeight:'28px'}],
      'button-14': ['14px', {lineHeight:'20px'}],
    },
    colors: {
        transparent: 'transparent',
        inherit: 'inherit',
        primary: {
          900: '#0C1131',
          500: '#3D53F5',
          50: '#ECEEFE',

          
        },
        
        
        success: {
          '100': '#E8F6EE',
          '200': '#D1EEDD',
          '300': '#A3DDBC',
          '400': '#5DC389', 
          '500': '#18A957',
          '600': '#11763D', 
          '700': '#0A4423',
          '800': '#052211',
          '900': '#021109', 
        },
        warning: {
          '100': '#FFF8EB',
          '200': '#FFF1D7',
          '300': '#FFE4AF',
          '400': '#FFCF74', 
          '500': '#FFBB38',
          '600': '#B38327', 
          '700': '#664B16',
          '800': '#33250B',
          '900': '#191306',
        },
        error: {
          '100': '#FCE8EC',
          '200': '#F9D0D9',
          '300': '#F2A2B3',
          '400': '#E95C7B', 
          '500': '#DF1642',
          '600': '#9C0F2E', 
          '700': '#59091A',
          '800': '#2D040D',
          '850': '#150F10',
          '900': '#160207',
        },
         grey: {
          '50': '#FCFCFC',
          '100': '#F5F5F5',
          '200': '#EEEEEE',
          '300': '#E0E0E0',
          '400': '#BDBDBD', 
          '500': '#9E9E9E',
          '600': '#757575', 
          '700': '#616161',
          '800': '#424242',
          '900': '#323232',
          '950': '#212121',
          'black': '#171717',
        },
        white: '#FFF',
        black: '#000'
      },

    extend: {
      screens: {
        '3xl': '1920px'
      },
    },
  },
  plugins: [],
}