/**
      Copyright (c) 2024 crate.monster
*/

import { useEffect, useState, useCallback } from 'react';

let listeners: ((setKey: string, data: { [key: string]: unknown }) => void)[] = [];

type State = {
  setting: { [key: string]: unknown } | null;
  error: string | null;
  loading: boolean;
};

export const useFigmaSetting = (key: string) => {
  const [state, setState] = useState<State>({
    setting: null,
    error: null,
    loading: true
  });

  const setThisSetting = useCallback(
    (setKey: string, data: { [key: string]: unknown }) => {
      if (key === setKey) {
        setState({
          setting: data,
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

  return [state.setting, state.error, state.loading, setFigmaSetting];
};

export default useFigmaSetting;
