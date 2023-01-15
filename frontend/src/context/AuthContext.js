import { createContext, useContext, useEffect, useState } from "react";
import { ACCESS_TOKEN } from "../constants";

const AuthContext = createContext({});

export const useAuth = () => useContext(AuthContext);

export const AuthContextProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    setUser(JSON.parse(localStorage.getItem(ACCESS_TOKEN)));
  }, []);

  const signup = async (email, password) => {};

  const login = async (email, password) => {};

  const logout = () => {
    setUser(null);
    localStorage.removeItem(ACCESS_TOKEN);
  };

  return (
    <AuthContext.Provider value={{ user, login, signup, logout, setUser }}>
      {children}
    </AuthContext.Provider>
  );
};
