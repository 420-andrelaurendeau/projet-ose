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
  },
  variants: {
    extend: {
      borderColor: ['focus-visible'],
      opacity: ['disabled'],
    },
  },
  plugins: [],
}