import {useWindowSize} from './useWindowSize';

export function useIsSmallScreen() {
  const windowSize:any = useWindowSize();
  return windowSize.width < 600;
}
