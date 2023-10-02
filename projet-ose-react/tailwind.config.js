/** @type {import('tailwindcss').Config} */
module.exports = {
  purge: ['./src/**/*.{js,jsx,ts,tsx}', './public/index.html'],
  darkMode: 'class',
  theme: {
    extend: {
      colors: {
        'blue': '#306bac',
        'orange': '#F57A00',
        'gray': '#D0CCD0',
        'offwhite': '#FBFCFF',
        'dark': '#121420',
        'softdark': '#1A1C23',
        'red' : '#FF0000',
        'darkwhite': '#F0F0F0',
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
    },
  },
  plugins: [],
}