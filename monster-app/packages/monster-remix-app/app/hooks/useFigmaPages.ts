/**
      Copyright (c) 2024 crate.monster
*/

import { useCallback, useEffect, useState } from 'react';

let listeners: ((setKey: string, data: { [key: string]: unknown }) => void)[] = [];

type State = {
  pages: { [key: string]: unknown } | null;
  error: string | null;
  loading: boolean;
};

export const useFigmaPages = (key: string) => {
  const [state, setState] = useState<State>({
    pages: null,
    error: null,
    loading: true
  });

  const setThisSetting = useCallback(
    (setKey: string, data: { [key: string]: unknown }) => {
      if (key === setKey) {
        setState({
          pages: data,
          error: null,
          loading: false
        });
      }
    },
    [setState, key]
  );

  useEffect(() => {
    listeners = [...listeners, setThisSetting];

    return () => {
      listeners = listeners.filter((l) => l !== setThisSetting);
    };
  }, [setThisSetting]);

  const setFigmaSetting = useCallback(
    (data: unknown) => {
      console.log(key, data);
    },
    [key]
  );

  return [state.pages, state.error, state.loading, setFigmaSetting];
};

export default useFigmaPages;
