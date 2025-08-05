

import './App.css';

import { createTheme, MantineProvider } from '@mantine/core';
import '@mantine/core/styles.css';
import '@mantine/carousel/styles.css';
import '@mantine/tiptap/styles.css';
import '@mantine/dates/styles.css';
import '@mantine/notifications/styles.css';
import { Notifications } from '@mantine/notifications';

import { Provider } from 'react-redux';
import Store from './Store';
import AppRoutes from './Pages/AppRoutes';

import ChatWidget from './Components/ChatWidget'; // ✅ Re-added

const theme = createTheme({
  focusRing: 'never',
  fontFamily: 'Poppins, sans-serif',
  primaryColor: 'brightSun',
  primaryShade: 4,
  colors: {
    brightSun: [
      '#fffbeb', '#fff3c6', '#ffe588', '#ffd149', '#ffbd20',
      '#f99b07', '#dd7302', '#b75006', '#943c0c', '#7a330d', '#461902',
    ],
    mineShaft: [
      '#f6f6f6', '#e7e7e7', '#d1d1d1', '#b0b0b0',
      '#888888', '#6d6d6d', '#5d5d5d', '#4f4f4f',
      '#454545', '#3d3d3d', '#2d2d2d',
    ],
  },
});

function App() {
  return (
    <Provider store={Store}>
      <MantineProvider defaultColorScheme="dark" theme={theme}>
        <Notifications position="top-center" zIndex={2001} />
        <AppRoutes />

        {/* ✅ Chat widget always visible */}
        <ChatWidget />
      </MantineProvider>
    </Provider>
  );
}

export default App;

