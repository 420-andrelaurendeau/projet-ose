/** @type {import('tailwindcss').Config} */
module.exports = {
  mode: 'jit',
  purge: ['./src/**/*.{js,jsx,ts,tsx}', './public/index.html'],
  darkMode: 'class',
  theme: {
    extend: {
      backgroundImage: (theme) => ({
        'logo': "./src/assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png",
        'logoDark': "./src/assets/images/Cegep-Andre-Laurendeau.png",
      }),
      animation: {
        slideInDown: 'slideInDown 0.5s ease-out',
        slideOutRight: 'slideOutRight 0.5s ease-out',
      },
      keyframes: {
        slideInDown: {
          '0%': { transform: 'translateY(-100%)', opacity: '0' },
          '100%': { transform: 'translateY(0)', opacity: '1' },
        },
        slideOutRight: {
          '0%': { transform: 'translateX(0)', opacity: '1' },
          '100%': { transform: 'translateX(100%)', opacity: '0' },
        },
      },
      colors: {
        'blue': {
          '50': '#f3f6fc',
          '100': '#e6eef8',
          '200': '#c8dbef',
          '300': '#98bce1',
          '400': '#6199cf',
          '500': '#3d7cba',
          '600': '#306bac',
          '700': '#254e7f',
          '800': '#22446a',
          '900': '#213a59',
          '950': '#16253b',
          DEFAULT: '#306bac'
        },
        'orange': {
          '50': '#fffaec',
          '100': '#fff4d3',
          '200': '#ffe5a5',
          '300': '#ffd16d',
          '400': '#ffb132',
          '500': '#ff970a',
          '600': '#f57a00',
          '700': '#cc5c02',
          '800': '#a1470b',
          '900': '#823c0c',
          '950': '#461c04',
          DEFAULT: '#f57a00'
        },
        'gray': '#D0CCD0',
        'lightgray': '#e7e7e7',
        'offwhite': '#FBFCFF',
        'dark': '#121420',
        'softdark': '#1A1C23',
        'red' : '#FF0000',
        'darkwhite': '#F0F0F0',
        'darkgray': '#21253a',
        'green': '#22d722',
        'darkergray': '#1A1C23',
      },
      backgroundColor: {
        'bleu': '#306bac',
        'red' : '#FF0000',
        'gray': '#727172',
      },
      placeholderColor: {
        'gray': '#727172',
        'orange': '#F57A00',
      },
    },
    screens: {
        'xxxs': '280px',
        'xxs': '320px',
        'xs': '475px',
        'sm': '640px',
        'md': '768px',
        'lg': '1024px',
        'xl': '1280px',
    },
  },
  variants: {
    extend: {
      borderColor: ['focus-visible'],
      opacity: ['disabled'],
      backgroundImage: ['dark'],
    },
  },
  plugins: [
  ],
}