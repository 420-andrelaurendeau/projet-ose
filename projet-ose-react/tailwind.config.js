/** @type {import('tailwindcss').Config} */
module.exports = {
  purge: ['./src/**/*.{js,jsx,ts,tsx}', './public/index.html'],
  darkMode: 'media', // Utilise le mode sombre en fonction des préférences du système
  theme: {
    extend: {
      colors: {
        'blue': '#306bac',
        'orange': '#F57A00',
        'gray': '#D0CCD0',
        'offwhite': '#FBFCFF',
        'dark': '#121420',
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
